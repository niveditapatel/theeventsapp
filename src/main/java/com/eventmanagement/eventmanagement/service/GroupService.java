package com.eventmanagement.eventmanagement.service;

import com.eventmanagement.eventmanagement.entity.Group;
import com.eventmanagement.eventmanagement.entity.Email;
import com.eventmanagement.eventmanagement.entity.User;
import com.eventmanagement.eventmanagement.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.StringTokenizer;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }

    public List<Group> saveGroups(List<Group> groups) {
        return groupRepository.saveAll(groups);
    }


    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

   public List<Group> getUsersingroup(String groupname) {
        return groupRepository.findByGroupname(groupname);
    }

    public List<User> findByEmail(String email) {
        return groupRepository.findByEmail(email);
    }

  /*  public List<String> getEmails(String groupusers)
    { List<String> list = null;

        String CSV = groupusers;
           StringTokenizer tokenizer = new StringTokenizer(CSV, ",");
        while (tokenizer.hasMoreTokens())
  { System.out.println(tokenizer.nextToken());
      list.add(tokenizer.nextToken());
  }
        return list;
    } */
}
