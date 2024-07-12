/**
 * throw : 던진다.
 * try..catch : 잡는다
 */
function runner(){
    try{
        console.log("hello");
        throw new Error('문제 생김');
        console.log('code factory');
    }catch(e){
        console.log('----------catch----')
        console.log(e);
    }
}


runner();