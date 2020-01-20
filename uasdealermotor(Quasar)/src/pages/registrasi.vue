<template>
    <q-page>
       <div class="row justify-center q-pt-md">
         <div class="col-md-6 col-xs-12">
            <q-card flat>
              <q-card-section>
                <div class="text-h5 q-pb-md">Register</div>
                <q-form
                  @submit="onSubmit"
                  @reset="onReset"
                  class="q-gutter-sm"
                >
                    <q-input
                    filled
                    v-model="kodeadmin"
                    label="Silahkan isi Kodeadmin"
                    lazy-rules
                    :rules="[ val => val && val.length > 0 || 'Kode Tidak Boleh Kosong']"
                  />
                          <q-input
                    filled
                    v-model="nama"
                    label="Silahkan isi Nama Lengkap"
                    lazy-rules
                    :rules="[ val => val && val.length > 0 || 'Nama Tidak Boleh Kosong']"
                  />
                          <q-input
                    filled
                    v-model="tglahir"
                    label="Silahkan isi Tanggal Lahir"
                    lazy-rules
                    :rules="[ val => val && val.length > 0 || 'Tanggal lahir Tidak Boleh Kosong']"
                  />
             <q-select filled v-model="jeniskelamin" :options="listgender" label="Pilih Gender" />
                          <q-input
                    filled
                    v-model="alamatskrng"
                    label="Silahkan isi Alamat Rumah"
                    lazy-rules
                    :rules="[ val => val && val.length > 0 || 'ALamat Tidak Boleh Kosong']"
                  />
                              <q-input
                    filled
                    v-model="nohp"
                    label="Silahkan isi nomor Smartphone"
                    lazy-rules
                    :rules="[ val => val && val.length > 0 || 'Nomor Smartphone Tidak Boleh Kosong']"
                  />
                              <q-input
                    filled
                    v-model="email"
                    label="Silahkan isi E-mail"
                    lazy-rules
                    :rules="[ val => val && val.length > 0 || 'E-Mail Tidak Boleh Kosong']"
                  />
                  <q-input
                  filled
                  type="password"
                  v-model="password"
                  label="Silahkan Ketikkan Password"
                  lazzy-rules
                  :rules="[
                   val => val !== '' && val !== null || 'password tidak boleh kosong'
                  ]"
                  />

                  <div>
                    <q-btn label="Daftar" type="submit" color="primary"/>
                    <q-btn label="Reset" type="reset" color="primary" flat class="q-ml-sm" />
                  </div>
                 </q-form>
              </q-card-section>
            </q-card>
         </div>
       </div>
    </q-page>
</template>

<script>
export default {
  name: 'Guest Layout',
  data () {
    return {
      kodeadmin: '',
      nama: '',
      tglahir: '',
      jeniskelamin: '',
      alamatskrng: '',
      nohp: '',
      email: '',
      listgender: [
        'Laki-Laki',
        'Perempuan'
      ],
      password: ''
    }
  },
  methods: {
    onSubmit () {
      try {
        this.$axios.post('admin/insert', {
          kodeadmin: this.kodeadmin,
          nama: this.nama,
          tglahir: this.tglahir,
          jeniskelamin: this.jeniskelamin,
          alamatskrng: this.alamatskrng,
          nohp: this.nohp,
          email: this.email,
          password: this.password
        }).then(res => {
          if (res.data.error) {
            this.showNotif(res.data.pesan, 'negative')
          } else {
            this.showNotif(res.data.pesan, 'positive')
          }
        })
      } catch (error) {
        console.log(error)
      }
    },
    onReset () {
      this.kodeadmin = ''
      this.nama = ''
      this.tglahir = ''
      this.jeniskelamin = ''
      this.alamatskrng = ''
      this.nohp = ''
      this.email = ''
      this.password = ''
    },
    showNotif (msg, color) {
      this.$q.notify({
        message: msg,
        color: 'green'
      })
    }
  }
}
</script>
