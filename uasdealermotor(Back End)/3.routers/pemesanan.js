const pesan = require ('express')()
const pesanController =require('../2.controller/pemesanan')

pesan.post('/insert',(req,res) =>{
    pesanController.simpanpesan(req.body)
    .then(result=>{
      res.json(result)
    }).catch(err =>{
      res.json(err)
    })
})
pesan.get('/select',(req,res) =>{
  pesanController.ambilpesan()
  .then(result=>{
    res.json(result)
  }).catch(err =>{
    res.json(err)
  })
})

pesan.put('/update/:id', (req, res) => {
  pesanController.updatepesan(req.params.id,req.body.kodemotor,req.body.username,
      req.body.jumlahbeli)
      .then(result => res.json(result))
      .catch(error => res.json(error))
})

pesan.delete('/delete/:id', (req, res) => {
  console.log(req.params.id)
  pesanController.hapuspesan(req.params.id)
      .then(result => res.json(result))
      .catch(error => res.json(error))
})

module.exports=pesan