package com.example.demo.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.example.demo.Entity.Parent;

import com.example.demo.Repository.ParentRepo;

import jakarta.servlet.http.HttpSession;

@Service
public class ParentServ {

    @Autowired
    private ParentRepo repo;

    public String signup(
            Parent parent,
            Model m)
    {
        Optional<Parent> temp =
                repo.findByEmail(
                        parent.getEmail());

        if(temp.isPresent())
        {
            m.addAttribute(
                    "error",
                    "Email already exists");

            return "ParentSignup";
        }

        parent.setCreatedAt(
                java.time.LocalDateTime.now());
        repo.save(parent);

        return "redirect:/parent/login";
    }

    public String login(
            Parent parent,
            Model m,
            HttpSession session)
    {
        Optional<Parent> temp =
                repo.findByEmail(
                        parent.getEmail());

        if(temp.isEmpty())
        {
            m.addAttribute(
                    "error",
                    "Invalid Email");

            return "Login";
        }

        Parent dbParent =
                temp.get();

        if(!dbParent.getPassword()
                .equals(parent.getPassword()))
        {
            m.addAttribute(
                    "error",
                    "Invalid Password");

            return "Login";
        }

        session.setAttribute(
                "parent",
                dbParent);

        return "redirect:/parenthome";
    }
}