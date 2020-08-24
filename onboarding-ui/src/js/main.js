const http = require('http');
const fs = require('fs');
const PORT_NUMBER = 9000;


http.createServer((req, res)=>{
    const filePath = __dirname + '/../index.html';
    console.log("filePath", filePath);
    fs.readFile(filePath, (err, htmlFile)=>{
        if(err){
            res.writeHead(404);
            res.end('Html not found')
        } else{
            res.writeHead(200, { 'Content-type': 'text/html'});
            res.write(htmlFile);
            res.end();
        }
    })
   }).listen(PORT_NUMBER);
