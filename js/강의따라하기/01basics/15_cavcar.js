const yujin1= {name : '유진'};
const yujin2=yujin1;

const yujin3 = {name:'유진'};

console.log(yujin1===yujin2)
console.log(yujin1===yujin3)
console.log(yujin2===yujin3)


/**
 * Spread Operator 
 */

const yujin4= {...yujin3}

console.log(yujin4);
console.log(yujin4===yujin3);


const yujin5={
    ...yujin3
    ,year:2003
}

console.log(yujin5);

//spread opreator는 순서가 중요 
const yujin6= {
    ...yujin3, name : "코드팩토리"
}

const yujin7 = {
    name : "코드팩토리" , ...yujin3
}


console.log(yujin6);
console.log(yujin7);


const numbers=[1,3,5];
const numbers2=[...numbers,10];
const numbers3=[10,...numbers];
console.log(numbers2);
console.log(numbers3);





