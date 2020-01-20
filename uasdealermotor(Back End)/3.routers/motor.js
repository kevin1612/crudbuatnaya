const motor = require ('express')()
const motorController =require('../2.controller/motor')

motor.post('/insert',(req,res) =>{
    motorController.simpanmotor(req.body)
    .then(result=>{
      res.json(result)
    }).catch(err =>{
      res.json(err)
    })
})
motor.get('/select',(req,res) =>{
  motorController.ambilmotor()
  .then(result=>{
    res.json(result)
  }).catch(err =>{
    res.json(err)
  })
})

motor.put('/update/:id', (req, res) => {
  motorController.updatemotor(req.params.id,req.body.kodemotor,req.body.namamotor,req.body.jenismotor,
      req.body.warna,req.body.stok,req.body.harga)
      .then(result => res.json(result))
      .catch(error => res.json(error))
})

motor.delete('/delete/:id', (req, res) => {
  console.log(req.params.id)
  motorController.hapusmotor(req.params.id)
      .then(result => res.json(result))
      .catch(error => res.json(error))
})

module.exports=motor