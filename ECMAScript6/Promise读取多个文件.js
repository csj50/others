//1. 引入 fs 模块
const fs = require("fs");

//回调地狱方式实现
// fs.readFile('./let关键字.html', (err1, data1)=>{
// 	fs.readFile('./const关键字.html', (err2, data2)=>{
// 		fs.readFile('./let关键字实例.html', (err3, data3)=>{
// 			let result = data1 + + '\r\n' + data2 + '\r\n' + data3;
// 			console.log(result);
// 		});
// 	});
// });

//使用 Promise 实现
//读取第一个文件
const p = new Promise((resolve, reason)=>{
	fs.readFile('./let关键字.html', (err1, data1)=>{
		//成功
		resolve(data1);
	});
});

//第一个Promise的回调
p.then(value=>{
	//返回一个新的Promise读取第二个文件
	return new Promise((resolve, reject)=>{
		fs.readFile("./const关键字.html", (err, data2)=>{
			//成功
			resolve([value, data2]);  //value是读第一个文件的返回，data2是读第二个文件的返回
		});
	});
}).then(value=>{
	return new Promise((resolve, reject)=>{
		fs.readFile("./let关键字实例.html", (err, data3)=>{
			//成功
			value.push(data3);  //压入
			resolve(value);  //value是三个文件的返回结果的数组
		});
	});
}).then(value=>{
	console.log(value.join('\r\n'));
});
