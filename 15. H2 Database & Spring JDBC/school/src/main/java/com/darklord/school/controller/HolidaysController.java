package com.darklord.school.controller;

import com.darklord.school.model.Holiday;
import com.darklord.school.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HolidaysController {
    @Autowired
    private HolidayRepository holidayRepository;
    @GetMapping("/holidays")
    public String displayHolidays(
            @RequestParam(required = false, defaultValue = "true") boolean festival,
            @RequestParam(required = false, defaultValue = "true") boolean federal,
            Model model
    ){
        model.addAttribute("festival", festival);
        model.addAttribute("federal", federal);
        List<Holiday> holidays = holidayRepository.findAllHolidays();
        Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type type : types) {
            model.addAttribute(type.toString(),
                    (holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));
        }
        return "holidays.html";
    }
}
