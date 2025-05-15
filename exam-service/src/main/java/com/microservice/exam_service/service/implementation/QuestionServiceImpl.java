package com.microservice.exam_service.service.implementation;


import com.microservice.exam_service.Repository.OptionRepository;
import com.microservice.exam_service.Repository.QuestionRepository;
import com.microservice.exam_service.Repository.TopicRepository;
import com.microservice.exam_service.dto.OptionDto;
import com.microservice.exam_service.dto.QuestionListDto;
import com.microservice.exam_service.dto.QuestionRequestDto;
import com.microservice.exam_service.dto.UserDto;
import com.microservice.exam_service.entity.Option;
import com.microservice.exam_service.entity.Question;
import com.microservice.exam_service.entity.Topic;
import com.microservice.exam_service.exception.custom.ResourceNotFoundException;
import com.microservice.exam_service.exception.custom.UserNotFoundException;
import com.microservice.exam_service.service.QuestionService;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final RemoteUserService userService;
    private final TopicRepository topicRepository;
    private final OptionRepository optionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, RemoteUserService userService, TopicRepository topicRepository, OptionRepository optionRepository) {
        this.questionRepository = questionRepository;
        this.userService = userService;
        this.topicRepository = topicRepository;
        this.optionRepository = optionRepository;
    }
    @Transactional
    @Override
    public void addQuestion(QuestionRequestDto questionRequestDto) {
        UserDto user = userService.getUserById(questionRequestDto.getUserId());

        Optional<Topic> optionalTopic = topicRepository.findById(questionRequestDto.getTopicId());
        Topic topic = optionalTopic.orElseThrow(()-> new UserNotFoundException("please Enter valid topic id"));

        Question question = Question.builder()
                .questionText(questionRequestDto.getQuestionText())
                .topic(topic)
                .createdBy(user.getId())
                .build();
        question = questionRepository.save(question);

        for (OptionDto option : questionRequestDto.getOptions()){
            Option option1 = Option.builder()
                    .optionText(option.getText())
                    .isCorrect(option.isCorrect())
                    .question(question)
                    .build();
            optionRepository.save(option1);
        }
    }
    @Transactional
    @Override
    public void addMultipleQuestion(QuestionListDto questionListDto){
        for(QuestionRequestDto requestDto : questionListDto.getQuestions()){
            UserDto user = userService.getUserById(requestDto.getUserId());
            Topic topic = topicRepository.findById(requestDto.getTopicId())
                    .orElseThrow(()-> new ResourceNotFoundException("Please enter valid topic id"));

            Question question = Question.builder()
                    .questionText(requestDto.getQuestionText())
                    .topic(topic)
                    .createdBy(user.getId())
                    .build();
            question = questionRepository.save(question);

            for (OptionDto dto: requestDto.getOptions()){
                Option option = Option.builder()
                        .optionText(dto.getText())
                        .question(question)
                        .isCorrect(dto.isCorrect())
                        .build();
                optionRepository.save(option);
            }
        }
    }
    @Transactional
    @Override
    public void addQuestionFromCsv(MultipartFile file) throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] data = line.split(",", -1);
                if (data.length < 8) continue;
                String questionText = data[0];
                String[] options = Arrays.copyOfRange(data, 1, 5);
                String correctLetter = data[5].trim();
                Long topicId = Long.parseLong(data[6].trim());
                Long userId = Long.parseLong(data[7].trim());
                Topic topic = topicRepository.findById(topicId)
                        .orElseThrow(() -> new ResourceNotFoundException("Please Input valid topic id"));
                UserDto user = userService.getUserById(userId);
                Question question = Question.builder()
                        .questionText(questionText)
                        .topic(topic)
                        .createdBy(user.getId())
                        .build();

                String[] label = {"A", "B", "C", "D"};
                List<Option> optionList = new ArrayList<>();

                for (int i = 0; i < 4; i++) {
                    boolean isCorrect = label[i].equalsIgnoreCase(correctLetter);
                    Option option = Option.builder()
                            .optionText(options[i])
                            .isCorrect(isCorrect)
                            .question(question)
                            .build();
                    optionList.add(option);

                }
                question.setOptions(optionList);
                questionRepository.save(question);
            }
        }
    }
    @Transactional
    @Override
    public void addQuestionFromExcel(MultipartFile file)throws IOException{
        try(Workbook workbook = new XSSFWorkbook(file.getInputStream())){
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            boolean isFirstRow = true;
            while(rows.hasNext()){
                Row currentRow = rows.next();
                if(isFirstRow){
                    isFirstRow = false;
                    continue;
                }
                String questionText = getCellValue(currentRow.getCell(0));
                String[] options = new String[4];
                for (int i=1; i<5; i++){
                    options[i-1] = getCellValue(currentRow.getCell(i));
                }
                String correctOption = getCellValue(currentRow.getCell(5));
                Long topicId = Long.parseLong( getCellValue(currentRow.getCell(6)));
                Long userId = Long.parseLong(getCellValue(currentRow.getCell(7)));

                Topic topic = topicRepository.findById(topicId)
                        .orElseThrow(()-> new ResourceNotFoundException("Please enter valdi topic id"));
                UserDto user = userService.getUserById(userId);
                List<Option> optionsList = new ArrayList<>();
                Question question = Question.builder()
                        .questionText(questionText)
                        .topic(topic)
                        .createdBy(user.getId())
                        .build();

                boolean correct = false;
                String[] level = {"A","B","C","D"};

                for(int i=0; i<4; i++){
                    Option opt = new Option();
                    opt.setOptionText(options[i]);
                    correct = correctOption.equalsIgnoreCase(level[i]);
                    opt.setCorrect(correct);
                    opt.setQuestion(question);
                    optionsList.add(opt);
                }
                question.setOptions(optionsList);
                questionRepository.save(question);
            }
        }
    }

    private String getCellValue(Cell cell){
        if(cell == null)return "";
        return switch (cell.getCellType()){
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default -> "";
        };
    }

}
