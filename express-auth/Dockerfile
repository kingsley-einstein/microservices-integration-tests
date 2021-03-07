FROM node:14-alpine
WORKDIR /app
COPY src ./src
COPY *.json ./
RUN npm install
COPY . .
EXPOSE 7880
ENTRYPOINT ["npm", "start"]