package s21.project.info21.controller;

import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import s21.project.info21.model.entities.Friends;
import s21.project.info21.model.entities.Peers;
import s21.project.info21.services.CommonService;
import s21.project.info21.services.PeersService;


@Controller
@RequestMapping("/friends")
public class FriendsController {

    @Autowired
    private CommonService<Friends, Long> commonServiceFriends;
    @Autowired
    private CommonService<Peers, String> commonServicePeers;

    @Autowired
    private PeersService peersService;

    @GetMapping()
    public String friends(Model model) {
        model.addAttribute("friends", commonServiceFriends.getAll());
        return "friends/viewall";
    }

    @GetMapping("/add")
    public String addFriendForm(Model model) {
        model.addAttribute("peers", commonServicePeers.getAll());
        model.addAttribute("friend", new Friends());
        return "friends/add";
    }

    @PostMapping()
    public String create(@ModelAttribute("friend") Friends friend, Model model) {
        String peer1 = friend.getPeer1();
        if (!peersService.existsByNickname(peer1)) {
            model.addAttribute("error", "Invalid peer1 value. Please specify an existing value.");
            return "friends/add";
        } else {
            try {
                commonServiceFriends.add(friend);
            } catch (GenericJDBCException e) {}
        }
        return "redirect:/friends";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        commonServiceFriends.deleteById(id);
        return "redirect:/friends";
    }

    @GetMapping("/update/{id}")
    public String updateFriendForm(@PathVariable Long id, Model model) {
        model.addAttribute("peers", commonServicePeers.getAll());
        Friends friend = commonServiceFriends.findById(id);
        if (friend != null) {
            model.addAttribute("friend", friend);
            return "friends/update";
        } else {
            return "redirect:/friends";
        }
    }

    @PostMapping("/update/{id}")
    public String updateFriend(@PathVariable Long id, @ModelAttribute("friend") Friends updatedFriend, Model model) {
        model.addAttribute("peers", commonServicePeers.getAll());
        Friends existingFriend = commonServiceFriends.findById(id);
        if (existingFriend != null) {
            // Обновляем только те поля, которые были предоставлены в форме обновления
            if (updatedFriend.getPeer1() != null) {
                existingFriend.setPeer1(updatedFriend.getPeer1());
            }
            if (updatedFriend.getPeer2() != null) {
                existingFriend.setPeer2(updatedFriend.getPeer2());
            }
            commonServiceFriends.add(existingFriend);
        }
        return "redirect:/friends";
    }

    @PostMapping("/update")
    public String updateFriend(@ModelAttribute("friend") Friends friend) {
        commonServiceFriends.add(friend);
        return "redirect:/friends";
    }

}



