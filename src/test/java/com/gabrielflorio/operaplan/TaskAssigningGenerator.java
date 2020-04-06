package com.gabrielflorio.operaplan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class TaskAssigningGenerator {

    public static final int BASE_DURATION_MINIMUM = 30;
    public static final int BASE_DURATION_MAXIMUM = 90;
    public static final int BASE_DURATION_AVERAGE = BASE_DURATION_MINIMUM + BASE_DURATION_MAXIMUM / 2;
    private static final int SKILL_SET_SIZE_MINIMUM = 2;
    private static final int SKILL_SET_SIZE_MAXIMUM = 4;

    protected Random random;

    public TaskAssigningSolution createTaskAssigningSolution(String fileName, int taskListSize, int skillListSize,
            int employeeListSize, int taskTypeListSize, int customerListSize) {
        random = new Random();
        TaskAssigningSolution solution = new TaskAssigningSolution();
        solution.setId(0L);

        createSkillList(solution, skillListSize);
        createCustomerList(solution, customerListSize);
        createEmployeeList(solution, employeeListSize);
        createTaskTypeList(solution, taskTypeListSize);
        createTaskList(solution, taskListSize);
        solution.setFrozenCutoff(0);

        return solution;
    }

    private void createSkillList(TaskAssigningSolution solution, int skillListSize) {
        List<Skill> skillList = new ArrayList<>(skillListSize);
        for (int i = 0; i < skillListSize; i++) {
            Skill skill = new Skill();
            skill.setId(i);
            String skillName = FakeData.skillName();
            skill.setName(skillName);
            skillList.add(skill);
        }
        solution.setSkillList(skillList);
    }

    private void createCustomerList(TaskAssigningSolution solution, int customerListSize) {
        List<Customer> customerList = new ArrayList<>(customerListSize);
        for (int i = 0; i < customerListSize; i++) {
            Customer customer = new Customer();
            customer.setId(i);
            String customerName = FakeData.customerName();
            customer.setName(customerName);
            customerList.add(customer);
        }
        solution.setCustomerList(customerList);
    }

    private void createEmployeeList(TaskAssigningSolution solution, int employeeListSize) {
        List<Skill> skillList = solution.getSkillList();
        List<Customer> customerList = solution.getCustomerList();
        Affinity[] affinities = Affinity.values();
        List<Employee> employeeList = new ArrayList<>(employeeListSize);
        int skillListIndex = 0;
        for (int i = 0; i < employeeListSize; i++) {
            Employee employee = new Employee();
            employee.setId(i);
            String fullName = FakeData.employeeName();
            employee.setFullName(fullName);
            int skillSetSize = SKILL_SET_SIZE_MINIMUM + random.nextInt(SKILL_SET_SIZE_MAXIMUM - SKILL_SET_SIZE_MINIMUM);
            if (skillSetSize > skillList.size()) {
                skillSetSize = skillList.size();
            }
            Set<Skill> skillSet = new LinkedHashSet<>(skillSetSize);
            for (int j = 0; j < skillSetSize; j++) {
                skillSet.add(skillList.get(skillListIndex));
                skillListIndex = (skillListIndex + 1) % skillList.size();
            }
            employee.setSkillSet(skillSet);
            Map<Customer, Affinity> affinityMap = new LinkedHashMap<>(customerList.size());
            for (Customer customer : customerList) {
                affinityMap.put(customer, affinities[random.nextInt(affinities.length)]);
            }
            employee.setAffinityMap(affinityMap);
            employeeList.add(employee);
        }
        solution.setEmployeeList(employeeList);
    }

    private void createTaskTypeList(TaskAssigningSolution solution, int taskTypeListSize) {
        List<Employee> employeeList = solution.getEmployeeList();
        List<TaskType> taskTypeList = new ArrayList<>(taskTypeListSize);
        for (int i = 0; i < taskTypeListSize; i++) {
            TaskType taskType = new TaskType();
            taskType.setId(i);
            String title = FakeData.taskTypeName();
            taskType.setTitle(title);
            taskType.setBaseDuration(
                    BASE_DURATION_MINIMUM + random.nextInt(BASE_DURATION_MAXIMUM - BASE_DURATION_MINIMUM));
            Employee randomEmployee = employeeList.get(random.nextInt(employeeList.size()));
            ArrayList<Skill> randomSkillList = new ArrayList<>(randomEmployee.getSkillSet());
            Collections.shuffle(randomSkillList, random);
            int requiredSkillListSize = 1 + random.nextInt(randomSkillList.size() - 1);
            taskType.setRequiredSkillList(new ArrayList<>(randomSkillList.subList(0, requiredSkillListSize)));
            taskTypeList.add(taskType);
        }
        solution.setTaskTypeList(taskTypeList);
    }

    private void createTaskList(TaskAssigningSolution solution, int taskListSize) {
        List<TaskType> taskTypeList = solution.getTaskTypeList();
        List<Customer> customerList = solution.getCustomerList();
        Priority[] priorities = Priority.values();
        List<Task> taskList = new ArrayList<>(taskListSize);
        Map<TaskType, Integer> maxIndexInTaskTypeMap = new LinkedHashMap<>(taskTypeList.size());
        for (int i = 0; i < taskListSize; i++) {
            Task task = new Task();
            task.setId(i);
            TaskType taskType = taskTypeList.get(random.nextInt(taskTypeList.size()));
            task.setTaskType(taskType);
            Integer indexInTaskType = maxIndexInTaskTypeMap.get(taskType);
            if (indexInTaskType == null) {
                indexInTaskType = 1;
            } else {
                indexInTaskType++;
            }
            task.setIndexInTaskType(indexInTaskType);
            maxIndexInTaskTypeMap.put(taskType, indexInTaskType);
            task.setCustomer(customerList.get(random.nextInt(customerList.size())));
            task.setReadyTime(0);
            task.setPriority(priorities[random.nextInt(priorities.length)]);
            taskList.add(task);
        }
        solution.setTaskList(taskList);
    }

}
