package lk.ijse.gdse.aad.spring_boot_coursework.controller;

import lk.ijse.gdse.aad.spring_boot_coursework.dto.BranchDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.entity.User;
import lk.ijse.gdse.aad.spring_boot_coursework.exception.InvalidException;
import lk.ijse.gdse.aad.spring_boot_coursework.exception.NotFoundException;
import lk.ijse.gdse.aad.spring_boot_coursework.reqANDresp.response.JWTAuthResponse;
import lk.ijse.gdse.aad.spring_boot_coursework.dto.UserDTO;
import lk.ijse.gdse.aad.spring_boot_coursework.reqANDresp.secure.SignIn;
import lk.ijse.gdse.aad.spring_boot_coursework.reqANDresp.secure.SignUp;
import lk.ijse.gdse.aad.spring_boot_coursework.service.AuthenticationService;
import lk.ijse.gdse.aad.spring_boot_coursework.service.BranchService;
import lk.ijse.gdse.aad.spring_boot_coursework.service.UserService;
import lk.ijse.gdse.aad.spring_boot_coursework.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
//@CrossOrigin(origins = "http://localhost:57160")
public class UserController {
    private final UserService userService;

    private final AuthenticationService authenticationService;

    private final BranchService branchService;

    private final Mapping mapping;
    @GetMapping("/health")
    public String healthTest(){
        return "Health Test";
    }

    @PostMapping("/signup")
    public ResponseEntity<JWTAuthResponse> signUp(@RequestBody SignUp signUpReq) {
        return ResponseEntity.ok(authenticationService.signUp(signUpReq));
    }

    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> signIn(@RequestBody SignIn signInReq) {
        System.out.println(signInReq.getEmail());
        return ResponseEntity.ok(authenticationService.signIn(signInReq));
    }
    @GetMapping("/getAll")
    public Collection<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }
    @DeleteMapping("/deleteUser/{user_id}")
    public boolean deleteUser(@PathVariable String user_id){
        return userService.deleteUser(user_id);
    }



    @GetMapping(value = "/getUserById",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserDTO> getUserById(@RequestPart String email){
        User user = userService.getUserEntityById(email);
        System.out.println(user);
        return ResponseEntity.ok(mapping.toUserDTO(user));
    }

    ////////////////////////////////////////////////////////////
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/branch")
    public ResponseEntity<?> saveBranch(@Validated @RequestBody BranchDTO branchDTO,
                                        BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            branchService.saveBranch(branchDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("New Branch Added Successfully.");
        } catch (InvalidException exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product Code Invalid");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | New Branch Added Unsuccessfully.\nMore Details\n"+exception);
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getBranches(){
        try {
            return ResponseEntity.ok(branchService.getAllBranches());
        } catch (NotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Branches not found.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Branches Details fetched Unsuccessfully.\nMore Reason\n"+exception);
        }
    }
}
