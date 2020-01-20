const pesanModel = require('../1.models/pemesanan')
const oi=require('mongoose').Types.ObjectId

exports.simpanpesan = (data) =>
  new Promise((resolve, reject) => {
          pesanModel.create(data)
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
      })

    exports.ambilpesan = () =>
    new Promise((resolve, reject) => {
      pesanModel.find()
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
              pesan: 'Gagal' 
            })
        })
    })

    exports.updatepesan = (id,kodemotor,un,jb) =>
  new Promise(async (resolve, reject) => {
  
  // let query = {_id: ObjectId(req.params.id)};
  // console.log(dataMk)
  console.log("disini nih")
  
  await pesanModel.update(
  { _id: oi(id) },
  {
  $set: {
  kodemotor: kodemotor,
  username: un,
  jumlahbeli: jb
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
  
  
  exports.hapuspesan = (id) =>
  new Promise(async (resolve, reject) => {
  await pesanModel.remove({_id: oi(id)})
  .then(res => {
  resolve ({error:false, pesan: 'Pemesanan Berhasil terhapus'})
  })
  .catch(res => {
  console.log(error)
  reject ({error:true, pesan: 'Gagal Menghapus Pemesanan'})
  })
  })
  
