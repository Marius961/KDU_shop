<template>
    <main class="container">
      <div class="row">
        <h2>Популярні товари</h2>
      </div>
      <div class="row">
        <card
          v-for="item in items"
          :key="'product' + item.id"
          class="col-12 col-sm-6 col-md-4 col-lg-3"
          :item="item"
        >
        </card>
      </div>
    </main>
</template>

<script>

  import ItemCard from '../components/ItemCard'
  export default {
    components: {
      'card': ItemCard
    },
    data() {
      return {
        items: []
      }
    },
    created() {
      this.items = [
        {
          id: 0,
          name: 'Футболка',
          quantity: 1,
          image: 'http://pngimg.com/uploads/tshirt/tshirt_PNG5450.png',
          price: 320,
          colors: [
            {
              id: 0,
              colorName: 'Black',
              colorCode: '#000000'
            },
            {
              id: 1,
              colorName: 'red',
              colorCode: 'red'
            },
            {
              id: 2,
              colorName: 'blue',
              colorCode: 'blue'
            }
          ],
          sizes: [
            {
              id: 0,
              sizeName: 'L'
            }
          ]
        },
        {
          id: 1,
          name: 'Футболка',
          quantity: 1,
          image: 'http://pngimg.com/uploads/tshirt/tshirt_PNG5450.png',
          price: 320,
          colors: [
            {
              id: 0,
              colorName: 'Black',
              colorCode: '#000000'
            },
            {
              id: 1,
              colorName: 'red',
              colorCode: 'red'
            },
            {
              id: 2,
              colorName: 'blue',
              colorCode: 'blue'
            }
          ],
          sizes: [
            {
              id: 0,
              sizeName: 'L'
            }
          ]
        },
        {
          id: 2,
          name: 'Футболка',
          quantity: 1,
          image: 'http://pngimg.com/uploads/tshirt/tshirt_PNG5450.png',
          price: 320,
          colors: [
            {
              id: 0,
              colorName: 'Black',
              colorCode: '#000000'
            },
            {
              id: 1,
              colorName: 'red',
              colorCode: 'red'
            },
            {
              id: 2,
              colorName: 'blue',
              colorCode: 'blue'
            }
          ],
          sizes: [
            {
              id: 0,
              sizeName: 'L'
            }
          ]
        },
        {
          id: 3,
          name: 'Футболка',
          quantity: 1,
          image: 'http://pngimg.com/uploads/tshirt/tshirt_PNG5450.png',
          price: 320,
          colors: [
            {
              id: 0,
              colorName: 'Black',
              colorCode: '#000000'
            },
            {
              id: 1,
              colorName: 'red',
              colorCode: 'red'
            },
            {
              id: 2,
              colorName: 'blue',
              colorCode: 'blue'
            }
          ],
          sizes: [
            {
              id: 0,
              sizeName: 'L'
            }
          ]
        },
        {
          id: 4,
          name: 'Футболка',
          quantity: 1,
          image: 'http://pngimg.com/uploads/tshirt/tshirt_PNG5450.png',
          price: 320,
          colors: [
            {
              id: 0,
              colorName: 'Black',
              colorCode: '#000000'
            },
            {
              id: 1,
              colorName: 'red',
              colorCode: 'red'
            },
            {
              id: 2,
              colorName: 'blue',
              colorCode: 'blue'
            }
          ],
          sizes: [
            {
              id: 0,
              sizeName: 'L'
            }
          ]
        }
      ]
    }
  }
</script>

<style scoped>
  @import "../assets/css/home.css";
</style>
