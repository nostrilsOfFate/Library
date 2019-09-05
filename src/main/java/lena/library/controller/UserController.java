package lena.library.controller;

import lena.library.dto.UserRegistrationDto;
import lena.library.model.User;
import lena.library.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@Slf4j
@ImportResource({"classpath:security/security.xml"})
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("user", new UserRegistrationDto());
		return "registration";
	}

	@PostMapping("/registration")
	public String registration(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
		BindingResult result) {

		log.info("Register new User: {}", userDto.toString());
		User existing = userService.findByEmail(userDto.getEmail());
		if (existing != null) {
			result.rejectValue("email", null,
				"There is already an account registered with that email");
		}

		if (result.hasErrors()) {
			return "registration";
		}

		userService.save(userDto);
		return "redirect:/registration?success";
	}

	@GetMapping("/")
	public String root() {
		return "redirect:/welcome";
	}

	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}

	@GetMapping("/user")
	public String userIndex() {
		return "user/index";
	}


}
