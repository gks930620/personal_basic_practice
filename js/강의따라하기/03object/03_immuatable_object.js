

const yuJin={
    name : '안유진'
    ,year : 2003,

    get age(){
        return new Date().getFullYear()-this.year;
    },
    set age(age){
        this.year=new Date.getFullYear-age;
    }
}

console.log(Object.isExtensible(yuJin));

yuJin.position='vocal';

Object.preventExtensions(yuJin);
console.log(Object.isExtensible(yuJin));
yuJin['groupName']='아이브';
console.log(yuJin);
delete yuJin['position'];
console.log(yuJin);


/**
 * Seal 
 */

console.log('------------------------');


const yuJin2={
    name : '안유진'
    ,year : 2003,

    get age(){
        return new Date().getFullYear()-this.year;
    },
    set age(age){
        this.year=new Date.getFullYear-age;
    }
}



console.log(yuJin2);
console.log(Object.isSealed(yuJin2));

Object.seal(yuJin2); //yuJin2 봉인

console.log(Object.isSealed(yuJin2));

yuJin2['groupName']='아이브';

delete yuJin2['name'];
console.log(yuJin2);


Object.defineProperty(yuJin2, 'name' , {
    value : '코드팩토리',
});
console.log(Object.getOwnPropertyDescriptor(yuJin2,'name'));
//값은 바뀜, seal은 configurable만 false로 바꾸는거. writable은 true니까 값 변경은 가능

//fronzen,freeze : seal에다가 값도 변경 못하게.











