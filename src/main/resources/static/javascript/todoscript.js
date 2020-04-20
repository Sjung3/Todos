let input = document.querySelector("#display-input");
let list = document.querySelector("#to-do-list");
let checkOrUncheckAll = document.querySelector(".check-uncheck")
let clearAll = document.querySelector(".clear-all");
let activeBtn = document.querySelector("#active");
let completedBtn = document.querySelector("#completed");
let allBtn = document.querySelector("#all");
let footer = document.querySelector(".footer");

let todoArray = [];
let newTodo = '';

window.addEventListener("load", (e) => {
    footerAndLineControl();
    axios.get('api/todos').then(resp => {
        todoArray = resp.data;
        for (let t of todoArray) {
            createElements(t);
            footerAndLineControl();
        }
    })
});

input.addEventListener("keypress", (e) => {
    const regex = (/\S/);
    if (e.key === "Enter" && input.value.match(regex)) {
        newTodo = input.value;
        axios.post('api/todos', {"input": newTodo}).then(resp => {
            createElements(resp.data);
            footerAndLineControl();
            todoArray.push(resp.data);
        });
        newTodo = '';
        input.value = "";
    }
});

list.addEventListener("click", (e) => {
    if (e.target.className == "delete") {
        const li = e.target.parentElement;
        let remove = li.children[1].innerHTML;
        for (let todo of todoArray) {
            if (todo.input == remove) {
                axios.post('api/todos/delete', {"input": todo.id})
            }
        }
        list.removeChild(li);
        footerAndLineControl();
    }
});

clearAll.addEventListener("click", function (e) {
    if (e.target.className == "clear-all") {
        let checkBox = document.querySelectorAll(".check-box");
        for (box of checkBox)
            if (box.checked == true) {
                const label = box.parentElement;
                const li = label.parentElement;
                let remove = li.children[1].innerHTML;
                list.removeChild(li);
                for (let todo of todoArray) {
                    if (todo.input == remove) {
                        axios.post('api/todos/delete', {"input": todo.id})
                    }
                }
                footerAndLineControl();
            }
    }
});

checkOrUncheckAll.addEventListener("click", (e) => {
    if (e.target.className == "check-uncheck") {
        let checkUncheck = document.querySelector(".check-uncheck");
        let checkBox = document.querySelectorAll(".check-box");
        if (checkUncheck.checked == true) {
            for (box of checkBox) {
                box.checked = true;
            }
        } else {
            for (box of checkBox) {
                box.checked = false;
            }
        }
        if (activeBtn.checked == true) {
            showActive();
        } else if (completedBtn.checked == true) {
            showCompleted();
        } else {
            showAll();
        }
        lineThrough();
        footerAndLineControl();
    }
});

list.addEventListener("click", (e) => {
    if (e.target.className == "check-box") {
        lineThrough();
        footerAndLineControl();
    }
    if (activeBtn.checked == true) {
        showActive();
    } else if (completedBtn.checked == true) {
        showCompleted();
    } else {
        showAll();
    }
});

allBtn.addEventListener("click", (e) => {
    if (e.target.id == "all") {
        showAll();
    }
});

activeBtn.addEventListener("click", (e) => {
    if (e.target.id == "active") {
        showActive();

    }
});

completedBtn.addEventListener("click", (e) => {
    if (e.target.id == "completed") {
        showCompleted();
    }
});


function showAll() {
    let checkBox = document.querySelectorAll(".check-box");
    for (box of checkBox) {
        const allBoxes = box;
        const label = allBoxes.parentElement;
        const parent = label.parentElement;
        parent.style.display = "grid"
    }
};

function showCompleted() {
    let checkBox = document.querySelectorAll(".check-box");
    for (box of checkBox) {
        if (box.checked == false) {
            const itIsNotChecked = box;
            const label = itIsNotChecked.parentElement;
            const parent = label.parentElement;
            parent.style.display = "none"
        } else {
            const itIsChecked = box;
            const label = itIsChecked.parentElement;
            const parent = label.parentElement;
            parent.style.display = "grid"
        }
    }
};

function showActive() {
    let checkBox = document.querySelectorAll(".check-box");
    for (box of checkBox) {
        if (box.checked == true) {
            const itIsChecked = box;
            const label = itIsChecked.parentElement;
            const parent = label.parentElement;
            parent.style.display = "none"
        } else {
            const itIsNotChecked = box;
            const label = itIsNotChecked.parentElement;
            const parent = label.parentElement;
            parent.style.display = "grid"
        }
    }
};

function lineThrough() {
    let checkBox = document.querySelectorAll(".check-box")
    for (box of checkBox) {
        if (box.checked == true) {
            const itIsChecked = box;
            const label = itIsChecked.parentElement;
            const parent = label.parentElement;
            const toDoLine = parent.children[1];

            toDoLine.style.textDecoration = "line-through";
            label.style.backgroundImage = "url(./css/checked.png)";
        } else {
            const itIsNotChecked = box;
            const label = itIsNotChecked.parentElement;
            const parent = label.parentElement;
            const toDoLine = parent.children[1];
            toDoLine.style.textDecoration = "none";
            label.style.backgroundImage = "url(./css/checkbox.png)";
        }
    }
};

function footerAndLineControl() {
    let checkBox = document.querySelectorAll(".check-box");
    let listLines = document.querySelectorAll(".inner-list");
    let remove = document.querySelector(".replace-check-box");
    let checkedBoxes = 0
    let nrOfItemsLeft = 0;
    let nrOflines = listLines.length;

    for (box of checkBox)
        if (box.checked == true) {
            checkedBoxes += 1;
        }

    if (checkedBoxes < 1) {
        clearAll.style.display = "none";
    } else {
        clearAll.style.display = "grid";
    }

    if (nrOflines < 1) {
        footer.style.display = "none";
        remove.style.display = "none";
    } else {
        footer.style.display = "grid";
        remove.style.display = "grid";
    }

    nrOfItemsLeft = nrOflines - checkedBoxes;

    if (nrOfItemsLeft == 1) {
        items.textContent = nrOfItemsLeft + " item left";
    } else if (nrOfItemsLeft > 1) {
        items.textContent = nrOfItemsLeft + " items left";
    } else {
        items.textContent = nrOfItemsLeft + " items left";
    }
};

function createElements(newTodo) {
    const inputElement = document.createElement("input");
    inputElement.type = "checkbox";
    inputElement.className = "check-box";

    const label = document.createElement("label");
    label.className = "check-box-label";
    label.style.backgroundImage = "url(css/checkbox.png)";

    const pElement = document.createElement("p");
    pElement.className = "input";
    //pElement.textContent = newTodo;
    pElement.innerText = newTodo.input;
    console.log(newTodo);


    label.appendChild(inputElement);

    const labelElement = document.createElement("label");
    labelElement.className = ("delete");

    const buttonELement = document.createElement("input");
    buttonELement.className = "delete-btn";
    buttonELement.type = "submit";
    buttonELement.value = "";

    labelElement.appendChild(buttonELement);

    const listElement = document.createElement("li");
    listElement.className = "inner-list";

    listElement.appendChild(label);
    listElement.appendChild(pElement);
    listElement.appendChild(labelElement);
    list.appendChild(listElement);
}
