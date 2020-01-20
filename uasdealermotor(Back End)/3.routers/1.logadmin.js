const admin = require ('express')()
const adminController =require('../2.controller/1.logadmin')

admin.post('/insert',(req,res) =>{
    adminController.simpanadmin(req.body)
    .then(result=>{
      res.json(result)
    }).catch(err =>{
      res.json(err)
    })
})
admin.get('/select',(req,res) =>{
  adminController.ambiladmin()
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


admin.put('/update/:id',(req,res)=>{
  adminController.updateadmin(req.params.id,req.body).then(result=>{
res.json(result)
  }).catch(err=>{
 res.json(err)
  })
})

admin.delete('/delete/:id', (req, res) => {
  console.log(req.params.id)
  adminController.deleteadmin(req.params.id)
      .then(result => res.json(result))
      .catch(error => res.json(error))
})





module.exports=admin