
var numberOne=20;
function levelOne(){
    console.log(numberOne);
} //함수실행시 함수밖에 변수 접근 가능

//levelOne();

function levelOne(){
    var numberOne=40;
    console.log(numberOne);
}
// levelOne();
// console.log(numberOne);

function levelOne(){
    var numberOne=40;
    function levelTwo(){
        var numberTwo=99;
        console.log(`lelve two  numberTwo : ${numberTwo}`);  //this아님 속성이아니라 변수값 
        console.log(`lelve two  numberOne : ${numberOne}`);
    }

    levelTwo();
    console.log(`levelOne numberOne : ${numberOne}`);
}

levelOne();
console.log(numberOne);
//console.log(numberTwo);  에러 함수안에서만 정의된 변수이기 때문

/**
 * Lexical Scope  면접 때 많이 나옴
 * 선언된 위치가 상위 스코프를 정한다
 * Dynamic Scope 
 * 실행된 위치가 상위 스코프를 정한다.
 * 
 * JS는 LexicalScope
 * 
 */

var numberThree=3;
function functionOne(){
    var numberThree=100;
    functionTwo();
}

function functionTwo(){
    console.log(numberThree);
}

functionOne();

var i=999;
for(var i=0 ; i<10 ; i++){
    console.log(i);
}

console.log(`${i}`);  //??  global에서도 i가 10으로 변함
//처음 강의할 떄 var 쓰지말라는게 이런 이유때문


i=999;
for(let i=0; i<10 ; i++){    //block level scope에서는 let으로  
    console.log(i);
}
console.log(`${i}`);  // let으로 하면 변경 안됨


/**
 * var 키워드는 함수 레벨 스코프만 만들어낸다.
 * let,const키워드는 함수레벨,블록레벨스코프 만들어낸다.
 */


