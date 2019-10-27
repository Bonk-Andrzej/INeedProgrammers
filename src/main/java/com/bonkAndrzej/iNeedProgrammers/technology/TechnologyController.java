package com.bonkAndrzej.iNeedProgrammers.technology;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController @Validated
@AllArgsConstructor
@RequestMapping("/api")
public class TechnologyController {
}
