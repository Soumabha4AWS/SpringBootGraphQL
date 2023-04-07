


















PostMapping 

mutation {
    createCourse(courseInput: {
                            id: "10",
                            courseName: "10",
                            rating: "10",
                            price: "10",
                            trainerName: "10",
                            numberOfDays: "10"
            }){
                            id
                            courseName
                            rating
                            price
                            trainerName
                            numberOfDays
              }
}
