const customer = require ('express')()
const cusController =require('../2.controller/1.logcustomer')

customer.post('/insert',(req,res) =>{
    cusController.simpancus(req.body)
    .then(result=>{
      res.json(result)
    }).catch(err =>{
      res.json(err)
    })
})
customer.get('/select',(req,res) =>{
  cusController.ambilcus()
  .then(result=>{
    res.json(result)
  }).catch(err =>{
    res.json(err)
  })
})


customer.post('/login',(req,res) =>{
  cusController.logincus(req.body)
  .then(result=>{
    res.json(result)
  }).catch(err =>{
    res.json(err)
  })
})



customer.put('/update/:id',(req,res)=>{
  cusController.updatecus(req.params.id,req.body).then(result=>{
res.json(result)
  }).catch(err=>{
 res.json(err)
  })
})

customer.post('/logindulu',(req,res) =>{
cusController.logindulu(req.body)
  .then(result=>{
    res.json(result)
  }).catch(err =>{
    res.json(err)
  })
})

customer.get('/select/:username', (req, res) => {
  cusController.getbyusername(req.params.username)
    .then(result => {
      res.json(result)
    }).catch(err => {
      res.json(err)
    })
})

module.exports=customer