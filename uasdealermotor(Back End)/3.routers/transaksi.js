const tran = require ('express')()
const tranController =require('../2.controller/transaksi')

tran.post('/insert',(req,res) =>{
    tranController.simpantran(req.body)
    .then(result=>{
      res.json(result)
    }).catch(err =>{
      res.json(err)
    })
})
tran.get('/select',(req,res) =>{
  tranController.ambiltran()
  .then(result=>{
    res.json(result)
  }).catch(err =>{
    res.json(err)
  })
})

module.exports=tran