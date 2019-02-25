<template>
    <div class="container">
      <div class="row">
        <div class="col-3 filters">
          <div class="row filters-group">
            <div class="col-12 group-name">Розмір</div>
            <div class="col-12 group-items">
              <label class="checkbox-container">
                <input type="checkbox">XS
                <span class="checkmark"></span>
              </label>
            </div>
            <div class="col-12 group-items">
              <label class="checkbox-container">
                <input type="checkbox">S
                <span class="checkmark"></span>
              </label>
            </div>
            <div class="col-12 group-items">
              <label class="checkbox-container">
                <input type="checkbox">L
                <span class="checkmark"></span>
              </label>
            </div>
            <div class="col-12 group-items">
              <label class="checkbox-container">
                <input type="checkbox">XL
                <span class="checkmark"></span>
              </label>
            </div>
            <div class="col-12 group-items">
              <label class="checkbox-container">
                <input type="checkbox">XXL
                <span class="checkmark"></span>
              </label>
            </div>
          </div>
          <div class="row filters-group">
            <div class="col-12 group-name">Колір</div>
            <div class="col-12 group-items">
              <label class="checkbox-container">
                <input type="checkbox">Чорний
                <span class="checkmark"></span>
              </label>
            </div>
            <div class="col-12 group-items">
              <label class="checkbox-container">
                <input type="checkbox">Білий
                <span class="checkmark"></span>
              </label>
            </div>
            <div class="col-12 group-items">
              <label class="checkbox-container">
                <input type="checkbox">Сірий
                <span class="checkmark"></span>
              </label>
            </div>
            <div class="col-12 group-items">
              <label class="checkbox-container">
                <input type="checkbox">Червоний
                <span class="checkmark"></span>
              </label>
            </div>
            <div class="col-12 group-items">
              <label class="checkbox-container">
                <input type="checkbox">Фіолетовив
                <span class="checkmark"></span>
              </label>
            </div>
          </div>
        </div>
        <div class="col-9">
          <div class="row">
            <card
              v-for="item in items"
              :key="'product' + item.id"
              class="col-4"
              :item="item"
            >
            </card>
          </div>
        </div>
      </div>
    </div>
</template>

<script>

  import ItemCard from '../components/ItemCard'

  export default {
    data() {
      return {
        items: []
      }
    },
    components: {
      'card': ItemCard
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
          id: 3,
          name: 'Чашка',
          quantity: 1,
          image: 'http://fgomsk.ru/files/image/poligrafiya/suvenirnaya/b/6.png',
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
          id: 4,
          name: 'Чашка',
          quantity: 1,
          image: 'http://purepng.com/public/uploads/large/purepng.com-cupcupopen-containertablewarecarrying-drinksceramic-cup-1701528266193sfx1o.png',
          price: 320,
          colors: [
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
  @import "../assets/css/products.css";
</style>
