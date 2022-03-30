package com.my.admin.app.commerce.model.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "edifices")
public class Edifice  {

    @Id
    private String id;

    @NotEmpty
    private String name;

    private List<Department> departments;

    private List<Expense> expenses;

    private List<ConsumedService> consumedServices;

    public Edifice() {
        this.consumedServices = new ArrayList<>();
        this.expenses = new ArrayList<>();
        this.departments = new ArrayList<>();
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<Department> getDepartments() { return departments; }

    public void setDepartments(List<Department> departments) { this.departments = departments; }

    public List<Expense> getExpenses() { return expenses; }

    public void setExpenses(List<Expense> expenses) { this.expenses = expenses; }

    public List<ConsumedService> getConsumedServices() { return consumedServices; }

    public void setConsumedServices(List<ConsumedService> consumedServices) { this.consumedServices = consumedServices; }

    public void addConsumedServices(ConsumedService consumedService) { this.consumedServices.add(consumedService); }

    public void addDepartment(Department department) { this.departments.add(department); }

    public void addExpense(Expense expense) { this.expenses.add(expense); }
}

