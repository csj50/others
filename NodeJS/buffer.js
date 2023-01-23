
// var str = "hello buffer";
// //将一个字符串保存到buffer中
// var buffer = Buffer.from(str);
// console.log(buffer);

// var str2 = "hello 学校";
// var buffer2 = Buffer.from(str2);
// console.log(str2.length);  //字符串的长度：8
// console.log(buffer2.length);  //占用内存的大小：12

var buffer3 = Buffer.alloc(10);  //创建一个10个字节的buffer
console.log(buffer3);
buffer3[0] = 88;
buffer3[1] = 255;
buffer3[2] = 0xaa;
console.log(buffer3);