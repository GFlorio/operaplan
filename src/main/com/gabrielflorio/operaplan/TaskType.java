package com.gabrielflorio.operaplan;

import java.util.ArrayList;
import java.util.List;

public class TaskType {

    private final long id;
    private String code;
    private String title;
    private int baseDuration; // In minutes

    private List<Skill> requiredSkillList;

    public TaskType(long id, String code, String title, int baseDuration) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.baseDuration = baseDuration;
        requiredSkillList = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBaseDuration() {
        return baseDuration;
    }

    public void setBaseDuration(int baseDuration) {
        this.baseDuration = baseDuration;
    }

    public List<Skill> getRequiredSkillList() {
        return requiredSkillList;
    }

    public void setRequiredSkillList(List<Skill> requiredSkillList) {
        this.requiredSkillList = requiredSkillList;
    }

    @Override
    public String toString() {
        return code;
    }

    public long getId() {
        return id;
    }
}
