<template>
    <q-page>
       <div class="row justify-center q-pt-md">
         <div class="col-md-4 col-xs-12" style="left: 280px; transform: translateY(35%);" >
            <q-card flat>
              <q-card-section>
                <div class="text-h5 q-pb-md flex flex-center">LOGIN DAFTARIN MENU</div>
                <q-form
                  @submit="onSubmit"
                  @reset="onReset"
                  class="q-gutter-sm"
                >
                  <q-input
                    filled
                    type="password"
                    v-model="koderoot"
                    label="Masukkan kode super admin "
                    lazy-rules
                    :rules="[
                      val => val !== null && val !== '' || 'Kode super tidak boleh Kosong'
                    ]"
                  />
                  <q-input
                  filled
                  type="password"
                  v-model="password"
                  label="ketik Password anda"
                  lazzy-rules
                  :rules="[
                   val => val !== '' && val !== null || 'Password tidak boleh kosong'
                  ]"
                  />

                  <div>
                    <q-btn label="LOGIN"  style="left: 170px; transform: translateY(-35%);" type="submit" color="teal"/>
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
      koderoot: '',
      password: ''
    }
  },
  methods: {
    onSubmit () {
      try {
        this.$axios.post('r/login', {
          koderoot: this.koderoot,
          password: this.password
        }).then(res => {
          if (res.data.error) {
            this.showNotif(res.data.pesan, 'negative')
          } else {
            this.showNotif(res.data.pesan, 'positive')
            this.$router.push('/registrasi')
          }
          this.koderoot = ''
          this.password = ''
        })
      } catch (error) {
        console.log(error)
      }
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
