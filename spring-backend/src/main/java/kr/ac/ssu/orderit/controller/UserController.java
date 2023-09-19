package kr.ac.ssu.orderit.controller;

import kr.ac.ssu.orderit.common.CommonResponse;
import kr.ac.ssu.orderit.common.StatusCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final StatusCode statusCode;

    @NotNull
    @PostMapping("/check")
    @ResponseBody
    public CommonResponse check(){
        return new CommonResponse(statusCode.SSU2000, null, statusCode.SSU2000_MSG);
    }
}
