let yujin={
    name : "안유진"
    , group : "아이브"
    ,dance : function(){
        return `${this.name}이 춤을 춥니다`;
    }
};

console.log(yujin)
console.log(yujin.dance());

const nameKey='name'
const yujin2={
    [nameKey] : "안유진"
}


/**
 * 객체 특징
 * 1. const로 선언할 경우 객체자체는변경불가
 * 2. 객체 안의 프로퍼티나 메서드는 변경 할 수 있다.
 * 
 */


console.log(yujin2);
console.log(Object.keys(yujin));
console.log(Object.values(yujin));

const name='안유진'
const yujin3= {
    name,
}
console.log(yujin3);








