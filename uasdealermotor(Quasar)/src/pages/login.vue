<template>
    <q-page>
       <div class="row justify-center q-pt-md">
         <div class="col-md-4 col-xs-12" style="left: 280px; transform: translateY(35%);" >
            <q-card flat>
              <q-card-section>
                <div class="text-h5 q-pb-md flex flex-center">SILAHKAN LOGIN DULU</div>
                <q-form
                  @submit="onSubmit"
                  @reset="onReset"
                  class="q-gutter-sm"
                >
                  <q-input
                    filled
                    type="text"
                    v-model="kodeadmin"
                    label="Ketik Kode Admin Anda "
                    lazy-rules
                    :rules="[
                      val => val !== null && val !== '' || 'Kode tidak boleh Kosong'
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
      kodeadmin: '',
      password: ''
    }
  },
  methods: {
    onSubmit () {
      try {
        this.$axios.post('admin/login', {
          kodeadmin: this.kodeadmin,
          password: this.password
        }).then(res => {
          if (res.data.error) {
            this.showNotif(res.data.pesan, 'negative')
          } else {
            this.showNotif(res.data.pesan, 'positive')
            this.$router.push('/home')
          }
          this.kodeadmin = ''
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
