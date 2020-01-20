const server = require('express')()
const bodyParser=require('body-parser')
const mongoose=require('mongoose')
const port = 5000
const cors=require('cors')
const mongoURI = 'mongodb://localhost:port:27017/uasdealer'

server.use(cors())

mongoose.connect(mongoURI,{
useNewUrlParser:true,
useCreateIndex:true,
useUnifiedTopology: true
}).then(()=>{
console.log('Connect to db Success')
}).catch(err=>{
console.log('Error:' +err)
})


server.use(bodyParser.json({
    extended:'true',
    limit:'50mb'
}))

server.use(bodyParser.urlencoded({
    extended:'true',
    limit:'50mb'
}))

//((server use))
server.use('/admin',require('./3.routers/1.logadmin'))
server.use('/customer',require('./3.routers/1.logcustomer'))
server.use('/motor',require('./3.routers/motor'))
server.use('/pemesanan',require('./3.routers/pemesanan'))
server.use('/transaksi',require('./3.routers/transaksi'))
server.use('/r',require('./3.routers/sr'))

server.listen(port, function() {
console.log('Server Started on Port ' + port)
})