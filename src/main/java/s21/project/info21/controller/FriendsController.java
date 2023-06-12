package s21.project.info21.controller;

import org.hibernate.exception.GenericJDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import s21.project.info21.model.entities.Friends;
import s21.project.info21.model.entities.Peers;
import s21.project.info21.services.CommonService;
import s21.project.info21.services.PeersService;
import static java.lang.constant.ConstantDescs.NULL;

@Controller
@RequestMapping("/friends")
public class FriendsController {
    private static final Logger log = LoggerFactory.getLogger(FriendsController.class);

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
                log.info("Created a new friend with ID {}", friend.getId());
            } catch (GenericJDBCException e) {
                log.error("Error while creating friend: ", e);
            }
        }
        return "redirect:/friends";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        commonServiceFriends.deleteById(id);
        log.info("Deleted friend with ID {}", id);
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
            if (updatedFriend.getPeer1() != NULL) {
                existingFriend.setPeer1(updatedFriend.getPeer1());
            }
            if (updatedFriend.getPeer2() != NULL) {
                existingFriend.setPeer2(updatedFriend.getPeer2());
            }
            commonServiceFriends.add(existingFriend);
            log.info("Updated friend with ID {}", existingFriend.getId());
        }
        return "redirect:/friends";
    }

    @PostMapping("/update")
    public String updateFriend(@ModelAttribute("friend") Friends friend) {
        commonServiceFriends.add(friend);
        log.info("Updated friend with ID {}", friend.getId());
        return "redirect:/friends";
    }
}
