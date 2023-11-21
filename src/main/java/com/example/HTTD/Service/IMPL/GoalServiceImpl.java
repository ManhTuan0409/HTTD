package com.example.HTTD.Service.IMPL;

import com.example.HTTD.Entity.Expense;
import com.example.HTTD.Entity.Goal;
import com.example.HTTD.Repository.GoalRepository;
import com.example.HTTD.Service.GoalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoalServiceImpl implements GoalService {

    GoalRepository goalRepository;

    @Override
    public Goal createGoal(Goal goal) {
        return goalRepository.save(goal);
    }

    @Override
    public Goal getGoalById(Long goalId) {
        Optional<Goal> optional = goalRepository.findById(goalId);
        return optional.orElse(null);
    }

    @Override
    public List<Goal> getAllGoal() {
        return goalRepository.findAll();
    }

    @Override
    public Goal updateGoal(Goal goal) {
        Goal existinggoal = goalRepository.findById(goal.getGoalId()).get();
        existinggoal.setGoalName(goal.getGoalName());
        existinggoal.setGoalAmount(goal.getGoalAmount());
        existinggoal.setCurrentAmount(goal.getCurrentAmount());
        existinggoal.setStartDay(goal.getStartDay());
        existinggoal.setEndDay(goal.getEndDay());
        Goal updatedGoal = goalRepository.save(existinggoal);
        return updatedGoal;
    }

    @Override
    public void deleteGoal(Long goalId) {
        goalRepository.deleteById(goalId);
    }
}
