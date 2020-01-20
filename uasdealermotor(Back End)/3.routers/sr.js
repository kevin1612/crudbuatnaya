const admin = require ('express')()
const adminController =require('../2.controller/root')

admin.post('/insert',(req,res) =>{
    adminController.simpanadmin(req.body)
    .then(result=>{
      res.json(result)
    }).catch(err =>{
      res.json(err)
    })
})


admin.post('/login',(req,res) =>{
  adminController.loginadmin(req.body)
  .then(result=>{
    res.json(result)
  }).catch(err =>{
    res.json(err)
  })
})




module.exports=admin