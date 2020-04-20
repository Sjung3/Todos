package com.todos;

//The API's request data is defines in this class
public class TodoData {
    private String input;

    //Makes other classes be able to access the private String input
    public String getInput() {
        return this.input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
