import {defineStore} from "pinia";
import axios  from "axios";
import {ApiUrl} from "@/helper/ApiHelper";


export const useUserStore = defineStore('user', {
    state: () => ({
        newUser:'',
        users: []
    }),
    actions: {
        async showUsers() {
            try {
                const userResponse = await axios.get(ApiUrl('/admin/users'), {
                    headers: {
                        'Authorization': 'Bearer ' + window.localStorage.getItem('accessToken')
                    }
                });
                console.log(userResponse.data);
                //this.users = userResponse.data;
                this.users = userResponse.data
                //const userId =userResponse.data.userId //hinzugefügt weil id undefined für delete und put
                console.log('users geladen', userResponse.data);
                const userIds = userResponse.data.map(user => user.userId);
                console.log('fuuuuuu...users ids ', userIds)
                return userResponse.data;
            } catch (error) {
                console.error('Error loading users:', error);
            }
        }, async creatUser(newUser) {
            console.log(newUser)

            const userResponse = await axios.post(ApiUrl('/admin/users'), newUser, {
                headers: {
                    'Authorization': 'Bearer ' + window.localStorage.getItem('accessToken')
                }
            })
            this.users.push(userResponse.data)

        }, async updateUser(userId, user) {

            const updateUser = await axios.put('http://localhost:8082/admin/users/' + userId, user, {
                    headers: {
                        'Authorization': 'Bearer ' + window.localStorage.getItem('accessToken')
                    }
                }
            )


        },

        /*async createUser(data) {
            console.log("!!!", data, data.admin);
            try {
                const userData = {
                    firstname: data.firstname,
                    lastname: data.lastname,
                    address: data.address,
                    mobile: data.mobile,
                    email: data.email,
                    password: data.password,
                    username: data.username,
                    isAdmin: data.isAdmin
                };
                console.log(userData);
                  const userResponse = await axios.post('http://localhost:8082/admin/users', userData);
                console.log(userResponse.data);
               //this.users.push(userResponse.data);
               return userResponse.data;
            } catch (error) {
                console.error('Error creating user:', error);
            }
            // editingUser, .value, .userId
        }, async updateUser(userId, updatedUser) {
            console.log('goshhhh', userId)
            try {
                const updateUserResponse = await axios.put(`http://localhost:8082/admin/users/${userId}`, updatedUser);
                console.log('User updated successfully:', updateUserResponse.data);
            } catch (error) {
                console.error('Error updating user:', error)
            }

        },*/ async deleteUser(userId) {
            try {
                console.log('userId in deleteUser der UserStore:', userId)
                const deleteUserResponse = await axios.delete(`http://localhost:8082/admin/users/${userId}`, {
                        headers: {
                            'Authorization': 'Bearer ' + window.localStorage.getItem('accessToken')
                        }
                    });
            } catch (error) {
                console.error('Fehler beim Löschen des Benutzers:', error)
            }
        },/*async deleteUser(userId){
            console.log(userId)
            const deleteUserResponse = await axios.delete('http://localhost:8082/admin/users/'+userId)
            this.showUsers()
        }'*/
        async addUserToCourse(userId, courseId) {
            console.log('store userId', userId)
            try {
                const addUserToCourseResponse = await axios.post(
                    `http://localhost:8082/admin/${userId}/book-course/${courseId}`,
                    {},
                    {
                        headers: {
                            'Authorization': 'Bearer ' + window.localStorage.getItem('accessToken')
                        }
                    },
                    {timeout: 5000} // Timeout in Millisekunden (hier 5 Sekunden)
                );
                console.log('store addUserToCourse', addUserToCourseResponse);
            } catch (error) {
                console.error('Error adding user to course:', error);
            }

        }
    }

});
