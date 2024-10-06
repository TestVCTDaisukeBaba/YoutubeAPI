package com.vicent.vtutor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmotionController {
	@GetMapping("/emotion")
	public String getTrend(Model model) throws Exception {
		model.addAttribute("message", "このページの担当は張さんです。");
		return "emotion";
	}
}