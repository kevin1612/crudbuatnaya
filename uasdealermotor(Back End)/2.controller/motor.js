const motorModel = require('../1.models/motor')
const oi=require('mongoose').Types.ObjectId

exports.simpanmotor = (data) =>
  new Promise((resolve, reject) => {

      motorModel.find({
        kodemotor:data.kodemotor
      }).then(hasil => {
        if (hasil.length > 0) {
          reject ({
            error: true,
            pesan: 'Kode Motor Sudah Digunakan'
          })
        } else {
          motorModel.create(data)
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

    exports.ambilmotor = () =>
    new Promise((resolve, reject) => {
      motorModel.find()
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
              pesan: 'Kode Motor Sudah Digunakan' 
            })
        })
    })

    exports.updatemotor = (id,kodemotor, namamotor, jenismotor, warna,
      stokm, harga) =>
  new Promise(async (resolve, reject) => {
  
  // let query = {_id: ObjectId(req.params.id)};
  // console.log(dataMk)
  console.log("disini nih")
  
  await motorModel.update(
  { _id: oi(id) },
  {
  $set: {
  kodemotor: kodemotor,
  namamotor: namamotor,
  jenismotor: jenismotor,
  warna: warna,
  stok:stokm,
  harga:harga
  }
  })
  .then(res => {
  resolve ({error: false,
  pesan: 'Data Berhasil Diupdate'})
  })
  .catch(res => {
  console.log("disini")
  reject({
  error: true,
  pesan: 'Gagal mengupdate data'
  })
  })
  })
  
  
  exports.hapusmotor = (id) =>
  new Promise(async (resolve, reject) => {
  await motorModel.remove({_id: oi(id)})
  .then(res => {
  resolve ({error:false, pesan: 'Data Motor Berhasil terhapus'})
  })
  .catch(res => {
  console.log(error)
  reject ({error:true, pesan: 'Gagal Menghapus Data'})
  })
  })
  
