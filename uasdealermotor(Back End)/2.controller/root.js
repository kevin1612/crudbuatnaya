const adminModel = require('../1.models/super')
const bcrypt = require('bcryptjs')
const oi=require('mongoose').Types.ObjectId

exports.simpanadmin = (data) =>
  new Promise((resolve, reject) => {
    bcrypt.hash(data.password, 10, (err, hash) => {
      data.password = hash
      adminModel.find({
        koderoot:data.koderoot
      }).then(hasil => {
        if (hasil.length > 0) {
          reject ({
            error: true,
            pesan: 'Kode Salah'
          })
        } else {
          adminModel.create(data)
        .then(res => {
          resolve({
            error: false,
            pesan: 'Berhasil Input' 
          })
        })
        .catch (() => {
            reject({
              error: true,
              pesan: 'Kode Salah' 
            })
        })
      }
    })
  })
})


exports.loginadmin = (data) =>
      new Promise((resolve, reject) => {
      adminModel.findOne({
          koderoot: data.koderoot
        }).then(res => {
          // console.log(res)
          // resolve(res)
          if(res === null) {
            reject({
              error: true,
              pesan: 'Kode Admin Tidak Terdaftar'
            })
          } else {
            let passwordHash = res.password
            if (bcrypt.compareSync(data.password, passwordHash)) {
              resolve({
                error: false,
                pesan: 'Berhasil Login',
                data:res
              })
            } else {
              reject({
                error: true,
                pesan: 'Gagal Login'
              })
            }
          }
        })
      })

   