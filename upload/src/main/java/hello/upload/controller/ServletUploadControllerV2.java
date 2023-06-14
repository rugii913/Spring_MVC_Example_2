package hello.upload.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("/servlet/v2")
public class ServletUploadControllerV2 {

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/upload")
    public String newFile() {
        return "upload-form";
    }

    @PostMapping("/upload")
    public String saveFileV1(HttpServletRequest request) throws ServletException, IOException {
        log.info("request={}", request);

        String itemName = request.getParameter("itemName");
        log.info("itemName={}", itemName);

        Collection<Part> parts = request.getParts();
        log.info("parts={}", parts);

        for (Part part : parts) {
            log.info("=== PART ===");
            log.info("name={}", part.getName());
            Collection<String> headerNames = part.getHeaderNames();
            for (String headerName : headerNames) {
                log.info("header {}:{}", headerName, part.getHeader(headerName));
            }
            //편의 메서드
            //content-disposition; filename //헤더 이름으로 꺼내도 바디가 길고 파싱하기 까다롭기 때문에 편의 메서드를 제공함
            log.info("submittedFileName={}", part.getSubmittedFileName());
            log.info("size={}", part.getSize()); //part body size

            //데이터 읽기
            InputStream inputStream = part.getInputStream();
            String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); //바이너리<->문자 변환 시에는 항상 Charset 정해야함
            log.info("body={}", body);

            //파일에 저장하기
            if (StringUtils.hasText(part.getSubmittedFileName())) {
                String fullPath = fileDir + part.getSubmittedFileName();
                log.info("파일 저장 fullPath={}", fullPath);

                part.write(fullPath);
            }

            inputStream.close();
            //강의에는 없는 코드지만 이 코드 없으면 에러
            //->인프런 질문&답변 문제해결 참고 https://www.inflearn.com/questions/756534/%ED%8C%8C%EC%9D%BC-%EC%97%85%EB%A1%9C%EB%93%9C-%EC%A4%91-%EC%97%90%EB%9F%AC-%EB%B0%9C%EC%83%9D

            //혹은 try (with resources)로도 가능할 듯하다.
            //https://stackoverflow.com/questions/76142662/csv-uploading-as-multipart-file-throws-failed-to-perform-cleanup-of-multipart-i
            //try (InputStream inputStream1 = part.getInputStream()) {/*~~~*/}
        }

        return "upload-form";
    }
}
