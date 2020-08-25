const http = require('http');
const fs = require('fs');
const path = require('path');

const PORT_NUMBER = 9000;


http.createServer((req, res)=>{
    const filePath = __dirname + '/../index.html';
    if(req.url === "/"){
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
    }else if(req.url.match("\.css$")){
        var cssPath = path.join(__dirname, '/../static', req.url);
            fs.readFile(cssPath, (err, cssFile)=>{
                if(err){
                    res.writeHead(404);
                    res.end('Html not found')
                } else{
                    res.writeHead(200, { 'Content-type': 'text/css'});
                    res.write(cssFile);
                    res.end();
                }
            })

    }else{
        res.writeHead(404, {"Content-Type": "text/html"});
        res.end("No Page Found");
    }

   }).listen(PORT_NUMBER);
