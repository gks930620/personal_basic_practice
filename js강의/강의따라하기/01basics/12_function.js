function calculate(number){
    
}

const multiply= (x,y)=>{
    return x*y;
}

// function multiply(x,y){
//     return x*y;
// }   //기능이 완전이 똑같지는 않지만..  일단 사용법자체는 비슷

const multiply5= x=>y=>z=>`x:${x} y:${y} z:${z}`;

console.log(multiply5 (2)(5)(7) );

function multiply6(x){
    return function(y){
        return function(z){
            return `x:${x} y:${y} z:${z}`;
        }
    }
}
console.log(multiply6(2)(5)(7) );


const multiply2=function(x,y){
    return x*y;
}


const multiplyThree= function(x,y,z){
    console.log(arguments);
    return x*y*z;
}

console.log('~~~~~~~~~~~~~~~~~~~~~~~~~~');
console.log(multiplyThree(4,5,6));

const multiplyAll=function(...arguments){
    return Object.values(arguments).reduce((a,b)=>a*b,1);
}
console.log(multiplyAll(3,4,5,6));

(function(x,y){
    console.log(x,y);
})(4,5)

console.log(typeof multiply);
console.log(multiply instanceof  Object);















