package com.vicent.vtutor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CaptionsController {
		@GetMapping("/captions")
		public String getCaptions(Model model) throws Exception {
			model.addAttribute("message", "このページの担当は川本さんです。");
			return "captions";
		}
}