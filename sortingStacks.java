//For example we want to sort a non-empty ArrayList<Integer> arr using 2 stacks stack1 and stack2

int size = arr.size();
int count = 0;  // keep track of number of elements being push to stack2
stack1.push(arr.get(0)); // put first element into stack1
for (int i = 1; i < size; i++){
    while (true){
        if (count == i || arr.get(i) <= stack1.peek()) {   // count == i is true when stack1 is empty
            stack1.push(arr.get(i));     // smaller value will be placed on top of stack1
            break;
        } else {
            stack2.push(stack1.pop());     // pop smaller value in stack1 and push to stack2
            count++;
        }
    }
    while (count > 0){  // move all the values from stack2 to stack1
        stack1.push(stack2.pop());
        count--;
    }
}

// place sorted value back to arr
for (int i = 0; i < arr.length; i++){
    arr.set(i, stack1.pop());
}

return arr 
