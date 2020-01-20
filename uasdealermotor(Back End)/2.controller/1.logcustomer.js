const cusModel = require('../1.models/1.logcustomer')
const bcrypt = require('bcryptjs')
const oi=require('mongoose').Types.ObjectId

exports.simpancus = (data) =>
  new Promise((resolve, reject) => {
    bcrypt.hash(data.password, 10, (err, hash) => {
      data.password = hash
      cusModel.find({
        username:data.username
      }).then(hasil => {
        if (hasil.length > 0) {
          reject ({
            error: true,
            pesan: 'Kode Admin Sudah Digunakan'
          })
        } else {
          cusModel.create(data)
        .then(res => {
          resolve({
            error: false,
            pesan: 'Berhasil Input Data' 
          })
        })
        .catch (() => {
            reject({
              error: true,
              pesan: 'Gagal Input Data' 
            })
        })
      }
    })
  })
})


exports.logincus = (data) =>
      new Promise((resolve, reject) => {
      cusModel.findOne({
          username: data.username
        }).then(res => {
          // console.log(res)
          // resolve(res)
          if(res === null) {
            reject({
              error: true,
              pesan: 'Username Tidak Terdaftar'
            })
          } else {
            let passwordHash = res.password
            if (bcrypt.compareSync(data.password, passwordHash)) {
              resolve({
                error: false,
                pesan: 'Berhasil Login'
              })
            } else {
              reject({
                error: true,
                pesan: 'Psssword Salah'
              })
            }
          }
        })
      })

    exports.ambilcus = () =>
    new Promise((resolve, reject) => {
      cusModel.find()
        .then(res => {
          resolve({
            error: false,
            pesan: 'Berhasil Mengambil Data',
            data:res
          })
        })
        .catch (() => {
            reject({
              error: true,
              pesan: 'Username Sudah Digunakan' 
            })
        })
    })



    exports.updatecus=(id,data)=>
    new Promise((resolve,reject)=>{
  cusModel.updateOne({
    _id: oi(id)
  },data).then((res)=>{
    console.log(res)
    resolve({
      error:false,pesan:'Berhasil Mengupdate Data'
    })
  }).catch(()=>{
    reject({
      error:true,pesan:'Gagal Mengubah Data'
    })
  })
    })

    exports.logindulu = (data) =>
      new Promise((resolve, reject) => {
        cusModel.findOne({
          username: data.username
        }).then(res => {
          // console.log(res)
          // resolve(res)
          if(res === null) {
            reject({
              error: true,
              pesan: 'Username Tidak Terdaftar'
            })
          } else {
            let passwordHash = res.password
            if (bcrypt.compareSync(data.password, passwordHash)) {
              resolve({
                error: false,
                pesan: 'Berhasil Menemukan Data',
                data:res
              })
            } else {
              reject({
                error: true,
                pesan: 'Password Salah'
              })
            }
          }
        })
      })

      exports.getbyusername = (username) =>
new Promise((resolve, reject) => {
  cusModel.findOne({
    username:username
  })
    .then(res => {
      resolve({
        eror:false,
        pesan: 'Berhasil Mengambil Data',
        data:res
      })
  }).catch(() => {
    reject({
      eror: true,
      pesan: 'Gagal Mengambil Data'
    })
  })
})