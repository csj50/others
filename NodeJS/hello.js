console.log("hello Node");

var obj = {};
obj.a = {};
var a = obj.a;
//a 和 obj.a 指向的是同一个对象
console.log(a === obj.a); //true