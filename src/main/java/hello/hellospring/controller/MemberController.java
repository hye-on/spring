package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MemberController {
    //private  final MemberService memberService =new MemberService();
    //여러 곳에서 쓰이기 때문에 객체를 하나만 생성하고 공용으로 사용하면 된다.
    private  final MemberService memberService;//스프링 컨테이너에 등록을 하고 사용. 딱하나만 등록이 된다.

//    @Autowired //setter주입
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }
    @Autowired  //생성자 주입을 쓰는 것이 좋다.
    public MemberController(MemberService memberService) {//생성자에서 Autowired를 쓰면 멤버 컨트롤러가 생성이 될때
        this.memberService = memberService;               //스프링 빈에 등록되어 있는 멤버 서비스 객체를 가져다가 넣어줌
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName((form.getName()));
       // System.out.println("member = " + member.getName());
        memberService.join(member);



        return "redirect:/";
    }


    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
