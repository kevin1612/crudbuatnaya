const tranModel = require('../1.models/transaksi')

exports.simpantran = (data) =>
  new Promise((resolve, reject) => {
          tranModel.create(data)
        .then(res => {
          resolve({
            error: false,
            pesan: 'Berhasil Input Transaksi' 
          })
        })
        .catch (() => {
            reject({
              error: true,
              pesan: 'Gagal Input Transaksi' 
            })
        })
      })


    exports.ambiltran = () =>
    new Promise((resolve, reject) => {
      tranModel.find()
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
              pesan: 'Gagal Mengambil Data' 
            })
        })
    })
    
  