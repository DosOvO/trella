<template>
  <div id="app">
    <div class="flex justify-center">
      <div class="min-h-screen flex overflow-x-scroll py-12">
        <div
            v-for="column in columns"
            :key="column.title"
            class="bg-gray-100 rounded-lg px-3 py-3 column-width rounded mr-4"
        >
          <div class="columnRow flex mt-4 justify-between items-center">
            <p class="text-gray-700 font-semibold font-sans tracking-wide text-sm">{{column.title}}</p>
            <button class="button text-gray-700 font-semibold font-sans tracking-wide text-sm" type="button">+</button>
          </div>
          <!-- Draggable component comes from vuedraggable. It provides drag & drop functionality -->
          <draggable :list="column.tasks" :animation="200" ghost-class="ghost-card" group="tasks" @change="change($event, column.title)">
            <!-- Each element from here will be draggable and animated.
            Note :key is very important here to be unique both for draggable and animations to be smooth & consistent. -->
            <task-card
                v-for="(task) in column.tasks"
                :key="task.id"
                :task="task"
                class="mt-3 cursor-move"
            ></task-card>
            <!-- </transition-group> -->
          </draggable>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import draggable from "vuedraggable";
import TaskCard from "./components/TaskCard.vue";
import { CLIENT } from "./assets/js/http";
export default {
  name: "App",
  components: {
    TaskCard,
    draggable
  },
  data() {
    return {
      columns: [
        {
          title: "ToDo",
          tasks: [
            {
              id: 2,
              title: "Provide documentation on integrations",
              status: "TODO",
              date: "Sep 12"
            },
            {
              id: 3,
              title: "Design shopping cart dropdown",
              date: "Sep 9",
              status: "TODO",
              type: "Design"
            },
            {
              id: 4,
              title: "Add discount code to checkout page",
              date: "Sep 14",
              status: "TODO",
              type: "Feature Request"
            },
            {
              id: 5,
              title: "Test checkout flow",
              date: "Sep 15",
              status: "TODO",
              type: "QA"
            }
          ]
        },
        {
          title: "Doing",
          tasks: [
            {
              id: 6,
              title: "Design shopping cart dropdown",
              date: "Sep 9",
              status: "DOING",
              type: "Design"
            },
            {
              id: 7,
              title: "Add discount code to checkout page",
              date: "Sep 14",
              status: "DOING",
              type: "Feature Request"
            },
            {
              id: 8,
              title: "Provide documentation on integrations",
              date: "Sep 12",
              status: "DOING",
              type: "Backend"
            }
          ]
        },
        {
          title: "Done",
          tasks: [
            {
              id: 9,
              title: "Provide documentation on integrations",
              date: "Sep 12",
              status: "DONE"
            },
            {
              id: 10,
              title: "Design shopping cart dropdown",
              date: "Sep 9",
              status: "DONE",
              type: "Design"
            },
            {
              id: 11,
              title: "Add discount code to checkout page",
              date: "Sep 14",
              status: "DONE",
              type: "Feature Request"
            },
            {
              id: 12,
              title: "Design shopping cart dropdown",
              date: "Sep 9",
              status: "DONE",
              type: "Design"
            },
            {
              id: 13,
              title: "Add discount code to checkout page",
              date: "Sep 14",
              status: "DONE",
              type: "Feature Request"
            }
          ]
        }
      ]
    };
  },
  methods: {
    getTasks() {
      CLIENT.get("/tasks").then(response => {

        for (let task of response.data) {
          const column = this.columns.find(column => column.title.toUpperCase() === task.status);

          let newTask = {
            id: task.id,
            title: task.description,
            status: task.status
          }

          column.tasks.push(newTask);

        }
      })

    },
    change(event, status) {
      // eslint-disable-next-line no-prototype-builtins
      if (event.hasOwnProperty("added")) {
        event.added.element.status = status.toUpperCase();

        let task = {
          id : event.added.element.id,
          description : event.added.element.title,
          status : event.added.element.status
        }

        CLIENT.put("/tasks", task)
            .then(response => console.log(response))
            .catch(err => console.log(err));
      }
    }
  },
  mounted() {
    this.getTasks()
  }
};
</script>

<style scoped>
.column-width {
  min-width: 320px;
  width: 450px;
}
/* Unfortunately @apply cannot be setup in codesandbox,
but you'd use "@apply border opacity-50 border-blue-500 bg-gray-200" here */
.ghost-card {
  opacity: 0.5;
  background: #F7FAFC;
  border: 1px solid #4299e1;
}
</style>
