const adminModel = require('../1.models/1.logadmin')
const bcrypt = require('bcryptjs')
const oi=require('mongoose').Types.ObjectId

exports.simpanadmin = (data) =>
  new Promise((resolve, reject) => {
    bcrypt.hash(data.password, 10, (err, hash) => {
      data.password = hash
      adminModel.find({
        kodeadmin:data.kodeadmin
      }).then(hasil => {
        if (hasil.length > 0) {
          reject ({
            error: true,
            pesan: 'Kode Admin Sudah Digunakan'
          })
        } else {
          adminModel.create(data)
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


exports.loginadmin = (data) =>
      new Promise((resolve, reject) => {
      adminModel.findOne({
          kodeadmin: data.kodeadmin
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

    exports.ambiladmin = () =>
    new Promise((resolve, reject) => {
      adminModel.find()
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
              pesan: 'Kode Admin Sudah Digunakan' 
            })
        })
    })

    exports.updateadmin=(id,data)=>
    new Promise((resolve,reject)=>{
  adminModel.updateMany({
    _id: oi(id)
  },data).then((res)=>{
    console.log(res)
    resolve({
      error:false,pesan:'Berhasil Mengupdate Data'
    })
  }).catch(()=>{
    reject({
      error:true,pesan:'Gagal Mengupdate Data'
    })
  })
    })

    exports.deleteadmin = (id) =>
    new Promise(async (resolve, reject) => {
    await adminModel.remove({_id: oi(id)})
    .then(res => {
    resolve ({error:false, pesan: 'Data Admin Berhasil terhapus'})
    })
    .catch(res => {
    console.log(error)
    reject ({error:true, pesan: 'Gagal Menghapus Data'})
    })
    })

    