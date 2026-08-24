package com.expensetracker.expense_tracker.controller;

import com.expensetracker.expense_tracker.model.Expense;
import com.expensetracker.expense_tracker.model.User;
import com.expensetracker.expense_tracker.repository.ExpenseRepository;
import com.expensetracker.expense_tracker.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "*")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;


    // =========================
    // GET USER'S EXPENSES
    // =========================

    @GetMapping
    public ResponseEntity<?> getUserExpenses(HttpSession session) {

        String username =
                (String) session.getAttribute("loggedInUser");

        if (username == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("User not logged in");
        }

        User user = userRepository
                .findByUsername(username)
                .orElse(null);

        if (user == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("User not found");
        }

        List<Expense> expenses =
                expenseRepository.findByUserId(user.getId());

        return ResponseEntity.ok(expenses);
    }


    // =========================
    // ADD EXPENSE
    // =========================

    @PostMapping
    public ResponseEntity<?> addExpense(
            @RequestBody Expense expense,
            HttpSession session) {

        String username =
                (String) session.getAttribute("loggedInUser");

        if (username == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("User not logged in");
        }

        User user = userRepository
                .findByUsername(username)
                .orElse(null);

        if (user == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("User not found");
        }

        // Attach expense to logged-in user
        expense.setUser(user);

        Expense savedExpense =
                expenseRepository.save(expense);

        return ResponseEntity.ok(savedExpense);
    }


    // =========================
    // UPDATE EXPENSE
    // =========================

    @PutMapping("/{id}")
    public ResponseEntity<?> updateExpense(
            @PathVariable Long id,
            @RequestBody Expense updatedExpense,
            HttpSession session) {

        String username =
                (String) session.getAttribute("loggedInUser");

        if (username == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("User not logged in");
        }

        User user = userRepository
                .findByUsername(username)
                .orElse(null);

        if (user == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("User not found");
        }

        Expense expense =
                expenseRepository.findById(id)
                        .orElse(null);

        if (expense == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Expense not found with id: " + id);
        }

        // Check ownership
        if (expense.getUser() == null ||
                !expense.getUser()
                        .getId()
                        .equals(user.getId())) {

            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("You are not allowed to edit this expense");
        }

        expense.setDescription(
                updatedExpense.getDescription()
        );

        expense.setAmount(
                updatedExpense.getAmount()
        );

        expense.setCategory(
                updatedExpense.getCategory()
        );

        expense.setDate(
                updatedExpense.getDate()
        );

        Expense savedExpense =
                expenseRepository.save(expense);

        return ResponseEntity.ok(savedExpense);
    }


    // =========================
    // DELETE EXPENSE
    // =========================

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpense(
            @PathVariable Long id,
            HttpSession session) {

        String username =
                (String) session.getAttribute("loggedInUser");

        if (username == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("User not logged in");
        }

        User user = userRepository
                .findByUsername(username)
                .orElse(null);

        if (user == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("User not found");
        }

        Expense expense =
                expenseRepository.findById(id)
                        .orElse(null);

        if (expense == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Expense not found with id: " + id);
        }

        // Check ownership
        if (expense.getUser() == null ||
                !expense.getUser()
                        .getId()
                        .equals(user.getId())) {

            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("You are not allowed to delete this expense");
        }

        expenseRepository.delete(expense);

        return ResponseEntity.ok(
                "Expense deleted successfully"
        );
    }
}